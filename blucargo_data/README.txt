************************************************************************
To create database run the following command

mvn sql:execute
***********************************************************************

***********************************************************************

DATABASE SETTINGS

***********************************************************************

In my.cnf


----------------------------------
[mysqld]  
#  
# * Basic Settings  
#  
  
lower_case_table_names=1  
---------------------------------

**********************************************************************

Population of the database can take from 2 to 10 minutes. If it takes longer 
than that, You may apply below settings to your mysql configuration file. 
On linux its /etc/mysql/my.cnf

innodb_data_file_path = ibdata1:2000M;ibdata2:10M:autoextend
# You can set .._buffer_pool_size up to 50 - 80 %
# of RAM but beware of setting memory usage too high
innodb_buffer_pool_size = 384M
innodb_additional_mem_pool_size = 20M
# Set .._log_file_size to 25 % of buffer pool size
innodb_log_file_size = 100M
innodb_log_buffer_size = 8M
innodb_flush_log_at_trx_commit = 1
innodb_lock_wait_timeout = 50 
