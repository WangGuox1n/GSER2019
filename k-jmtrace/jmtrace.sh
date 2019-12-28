#!/bin/bash

basepath=$(cd `dirname $0`; pwd)

java -javaagent:${basepath}/target/traceagent.jar $*
