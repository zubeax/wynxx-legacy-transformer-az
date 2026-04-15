/*
 * MQXCOPY - copy records from //SYS010 (input) to //SYS050 (output)
 *
 * Assumptions:
 *  - Input and output are MVS datasets allocated to DD names SYS010 and SYS050.
 *  - Typical use is RECFM=FBA,LRECL=133 (print lines with ASA CC). We copy bytes as-is.
 *  - We purposely open in record mode to preserve record boundaries and avoid codepage translation.
 *
 * Build: compile with IBM XL C/Open XL C with DEBUG info enabled for z/OS Debugger.
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LRECL 133

static void die(const char* msg){
    fprintf(stderr, "MQXCOPY: %s\n", msg);
    exit(12);
}

int main(void){
    /* Open by DD name in record mode, disable seeking for performance */
    FILE *in  = fopen("dd:SYS010", "rb,type=record,noseek");
    if(!in) die("failed to open dd:SYS010");

    FILE *out = fopen("dd:SYS050", "wb,type=record,noseek");
    if(!out) die("failed to open dd:SYS050");

    unsigned char buf[LRECL];
    size_t nrec=0;

    for(;;){
        /* fread in record mode returns the record into the buffer as one unit */
        size_t r = fread(buf, 1, sizeof(buf), in);
        if(r==0){
            if (feof(in)) break; /* normal end */
            perror("fread");
            die("read error");
        }
        /* If the dataset is exactly LRECL=133, r should be 133; if it's shorter, pad with blanks */
        if(r < sizeof(buf)){
            memset(buf + r, ' ', sizeof(buf) - r);
        }
        size_t w = fwrite(buf, 1, sizeof(buf), out);
        if(w != sizeof(buf)){
            perror("fwrite");
            die("write error");
        }
        nrec++;
    }

    fflush(out);
    fprintf(stderr, "MQXCOPY: copied %zu records of %d bytes\n", nrec, LRECL);
    return 0;
}
