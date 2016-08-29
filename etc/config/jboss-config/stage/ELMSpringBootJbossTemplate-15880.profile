ORACLE_HOME="/home/oracle/app/oracle/product/11.2.0/client_1"
export ORACLE_HOME

TNS_ADMIN="${ORACLE_HOME}/network/admin"
export TNS_ADMIN

LD_LIBRARY_PATH="${ORACLE_HOME}/lib"
export LD_LIBRARY_PATH

NLS_LANG=AMERICAN_AMERICA.UTF8
export NLS_LANG

ulimit -c unlimited
ulimit -n 65536
