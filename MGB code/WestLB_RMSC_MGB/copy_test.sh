#!/bin/sh
if [ "$1" = "" ]
then echo COB nicht gesetzt;exit
fi
mkdir $1
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SMB.$1*dat $1/smt_mgb_bond_eaa_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SMR.$1*dat $1/smt_mgb_repo_eaa_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SMG.$1*dat $1/smt_mgb_bond_pag_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SRG.$1*dat $1/smt_mgb_repo_pag_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SMU.$1*dat $1/smt_mgb_bond_na_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SRU.$1*dat $1/smt_mgb_repo_na_$1.dat
COB=$1
((COB+=1))
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SBC.$COB*dat $1/smt_mgb_bond_cbb_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SRC.$COB*dat $1/smt_mgb_repo_cbb_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/TMD.$COB*dat $1/smt_mgb_mm_eaa_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/TMG.$COB*dat $1/smt_mgb_mm_pag_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SMC.$COB*dat $1/smt_mgb_mm_cbb_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/TFD.$COB*dat $1/smt_mgb_fx_eaa_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/TFG.$COB*dat $1/smt_mgb_fx_pag_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SFC.$COB*dat $1/smt_mgb_fx_cbb_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SDD.$COB*dat $1/smt_mgb_drv_eaa_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SDG.$COB*dat $1/smt_mgb_drv_pag_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SDC.$COB*dat $1/smt_mgb_drv_cbb_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SDU.$COB*dat $1/smt_mgb_drv_na_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/TMU.$COB*dat $1/smt_mgb_mm_na_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/TFU.$COB*dat $1/smt_mgb_fx_na_$1.dat
scp frax2173:/p/a223/sp1e/mgb/mgbdata/mgbPrd/archiv/SAM.$COB*dat $1/smt_mgb_amend_$1.dat
