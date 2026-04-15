      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Application values to be treated as "constants"
      *****************************************************************
           05  CON-Landing-Program-Name   pic x(08) value 'ELISTP'.
           05  CON-Sign-On-Program-Name   pic x(08) value 'ESONP'.
           05  CON-Menu-Program-Name      pic x(08) value 'EMENUP'.
           05  CON-List-Program-Name      pic x(08) value 'ELISTP'.
           05  CON-View-Program-Name      pic x(08) value 'EVIEWP'.
           05  CON-Add-Program-Name       pic x(08) value 'EADDP'.
           05  CON-Update-Program-Name    pic x(08) value 'EUPDP'.
           05  CON-Audit-Program-Name     pic x(08) value 'EAUDP'.
           05  CON-Activity-Monitor       pic x(08) value 'EACTMON'.
           05  CON-Sign-On-TransId        pic x(04) value 'ESON'.
           05  CON-List-TransId           pic x(04) value 'ELST'.
           05  CON-Menu-TransId           pic x(04) value 'EMNU'.
           05  CON-Filters-TransId        pic x(04) value 'EFIL'.
           05  CON-View-TransId           pic x(04) value 'EDET'.
           05  CON-Add-TransId            pic x(04) value 'EADD'.
           05  CON-Update-TransId         pic x(04) value 'EUPD'.
           05  CON-Audit-TransId          pic x(04) value 'EAUD'.
           05  CON-Audit-Request-Id       pic x(08) value 'EAUDITRQ'.
           05  CON-ESONRUL-File-Name      pic x(08) value 'ESONRUL'.
           05  CON-ESONRUL-QName          pic x(16) value 'ESONRUL'.
           05  CON-ESONRUL-Item-Number    pic s9(4) binary value +1.
           05  CON-ESONRUL-RRN            pic s9(9) binary value +1.
           05  CON-EREGUSR-File-Name      pic x(08) value 'EREGUSR'.
           05  CON-EUACTTS-Queue-Prefix   pic x(08) value 'EUSERACT'.
           05  CON-EUACTTS-Item-Number    pic s9(4) binary value +1.
           05  CON-EMPMAST-File-Name      pic x(08) value 'EMPMAST'.
           05  CON-EMPMAST-Primary-Name-Path
                                          pic x(08) value 'PRIMNAME'.
           05  CON-Audit-File-Name        pic x(08) value 'EAUDIT'.                               
           05  CON-Monitor-Channel-Name   pic x(16)
               value 'DFHTRANSACTION'.
           05  CON-Monitor-Container-Name pic x(16)
               value 'MonitorContainer'.
           05  CON-List-Channel-Name      pic x(16)
               value 'DFHTRANSACTION'.
           05  CON-List-Container-Name    pic x(16)
               value 'ListContainer'.
           05  CON-APP-Channel-Name       pic x(16)
               value 'AppChannel'.
           05  CON-APP-Container-Name     pic x(16)
               value 'AppContainer'.        
           05  CON-Sign-On-Map-Name       pic x(8) value 'ESONM'.
           05  CON-Sign-On-Mapset-Name    pic x(8) value 'ESONMAP'.
           05  CON-Filters-Map-Name       pic x(8) value 'EFILM'.
           05  CON-List-Map-Name          pic x(8) value 'ELSTM'.
           05  CON-List-Mapset-Name       pic x(8) value 'ELSTMAP'.
           05  CON-Menu-Map-Name          pic x(8) value 'EMNUM'.
           05  CON-Menu-Mapset-Name       pic x(8) value 'EMNUMAP'.
           05  CON-View-Map-Name          pic x(8) value 'EDETM'.
           05  CON-View-Mapset-Name       pic x(8) value 'EDETMAP'.
           05  CON-Add-Map-Name           pic x(8) value 'EADDM'.
           05  CON-Add-Mapset-Name        pic x(8) value 'EADDMAP'.
           05  CON-Update-Map-Name        pic x(8) value 'EUPDM'.
           05  CON-Confirm-Delete-Map-Name
                                          pic x(8) value 'EDELM'.
           05  CON-Update-Mapset-Name     pic x(8) value 'EUPDMAP'.
           05  CON-Registered-Users-Filename pic x(8) value 'EREGUSR'.                               
           05  CON-Registered-Users-Filename pic x(8) value 'EREGUSR'.        