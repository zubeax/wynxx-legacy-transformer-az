#!/bin/bash
zowe zos-files upload file-to-pds helloworld.cbl USER.COBOL(HELLOWRLD)
zowe zos-jobs submit local-file compile.jcl --wait
zowe zos-jobs submit local-file run.jcl --wait
