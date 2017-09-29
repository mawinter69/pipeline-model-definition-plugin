/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

pipeline {
    environment {
        SOME_VAR = "SOME VALUE"
        CRED3 = credentials("cred3")
        INBETWEEN = "Something ${CRED3} between"
        CRED2 = credentials("cred2")
        OTHER_VAR = "OTHER VALUE"
    }

    agent any

    stages {
        stage("foo") {
            steps {
                echo "SOME_VAR is $SOME_VAR"
                echo "OTHER_VAR is $OTHER_VAR"
                echo "INBETWEEN is $INBETWEEN"
                writeFile file: "inbetween.txt", text: "${INBETWEEN}"
                writeFile file: "cred3.txt", text: "${CRED3}"
                writeFile file: "cred2.txt", text: "${CRED2}"
                archive "**/*.txt"
            }
        }
    }
}