sqlplus mgb_prd_read/mgb_prd_read@rmmgbprd @extract_mgb_profile_for_guard.sql mgbprofile.csv
sqlplus mgb_prd_read/mgb_prd_read@rmmgbprd @extract_mgb_privilege_for_guard.sql mgbprivilege.csv
sqlplus mgb_prd_read/mgb_prd_read@rmmgbprd @extract_mgb_identity_for_guard.sql mgbidentity.csv