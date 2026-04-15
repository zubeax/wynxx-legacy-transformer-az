# IMS HALDB/PHIDAM + JDBC + Spring Boot + JCL Bundle

This archive collects all artifacts we generated:

- **HALDB/DBRC JCL** to register a PHIDAM HALDB master and its partitions (DSPURX00)
- **IDCAMS JCL** to allocate HALDB partition data sets:
  - PHIDAM **database data sets** (segment data) as **VSAM ESDS**
  - PHIDAM **primary index** as **VSAM KSDS**
  - **ILDS** as **VSAM KSDS**
  - **PSINDEX** partitions as **VSAM KSDS**
- **DFSUPNT0** JCL to initialize HALDB partitions
- A sample **HALDB DBD** (PHIDAM) macro
- A stand‑alone **Java JDBC** sample (`ImsJdbcDemo.java`) using the **IMS Universal JDBC driver**
- A **Spring Boot** application with pooled DataSource and REST endpoints (TLS‑ready)
- **COBOL** programs (benchmark harness and BMP test program)

## References
- HALDB partition data sets (PHIDAM data=OSAM/VSAM ESDS; ILDS/Indexes=VSAM KSDS):  
  https://www.ibm.com/docs/en/ims/15.6.0?topic=databases-haldb-partition-data-sets
- HDAM/HIDAM/PHIDAM overview:  
  https://www.ibm.com/docs/en/SSEPH2_13.1.0/com.ibm.ims13.doc.dag/ims_damdball.htm
- Allocating PSINDEX (required `INDEXED`, `RECORDSIZE`, `KEYS`, `REUSE`):  
  https://www.ibm.com/docs/en/ims/15.6.0?topic=haldb-allocating-psindex-vsam-ksds-data-sets
- DFSUPNT0 (HALDB partition initialization):  
  https://www.ibm.com/docs/en/ims/15.4.0?topic=utilities-haldb-partition-data-set-initialization-utility-dfsupnt0
- DBRC `INIT.DB TYPHALDB` and `INIT.PART`:  
  https://www.ibm.com/docs/en/ims/15.4.0?topic=commands-initdb-command  
  https://www.ibm.com/docs/en/ims/15.4.0?topic=commands-initpart-command
- HALDB data set naming (`A00001`, `L00001`, `X00001`):  
  https://www.ibm.com/docs/en/ims/15.6.0?topic=sets-naming-convention-haldb-data-set-names
- IMS Universal JDBC DataSource properties:  
  https://www.ibm.com/docs/en/ims/15.6.0?topic=driver-connecting-ims-database-using-jdbc-datasource-interface  
  https://www.ibm.com/docs/en/ims/15.5.0?topic=ciuiujd-connecting-ims-database-by-using-jdbc-drivermanager-interface

## Layout
```
ims/
  haldb/
    01_dbrc_init_haldb.jcl
    02_idcams_alloc_data_esds.jcl
    03_idcams_alloc_primary_index_ksds.jcl
    04_idcams_alloc_ilds_ksds.jcl
    05_dfsupnt0_init_partitions.jcl
    06_idcams_alloc_psindex_ksds.jcl
  ddl/
    dbd_custordp_phidam.mac
java/
  jdbc/ImsJdbcDemo.java
spring-boot/ims-jdbc-spring/
  pom.xml
  src/main/java/com/example/ims/... (sources)
  src/main/resources/application.yml
cobol/
  CUSTORDBM.cbl
  CUSTORDPG.cbl
README.md
```

## Usage
1. Tailor DSNs/volsers/keys in JCL to your standards.
2. Run `ims/haldb/01_dbrc_init_haldb.jcl` (register master+parts in DBRC).
3. Check `LIST.PART` for partition IDs (e.g., `00001`, `00002`).
4. Allocate datasets with jobs `02`–`06`, matching suffix digits to IDs.
5. Initialize partitions with `05_dfsupnt0_init_partitions.jcl`.
6. Build PSINDEX as needed after load (allocation included).
7. Compile/run Java apps; set IMS Connect/TLS props in `application.yml`.
