create or replace force view v05_guard_profile 
as
select t29_id||'_'||t09_id as profile
  from t29_role, t09_mandant
 where t29_id <> 'TRADER'
   and t09_id <> 'GLB';


create or replace force view v04_guard_previlege 
as
select lower(t01.t01_nt_id) as nt_id, 
       t17.fk_t17_t29_role ||'_'||t17.fk_t17_t09_mandant as profile,
       t17.T17_CREATION_DATE as start_date,
       t17.fk_t17_t29_role ||' '||t09.T09_NAME as description
  from t01_employee t01 
  join t17_user_role t17 on t17.fk_t17_t01_employee = t01.t01_id 
   and fk_t17_t29_role <> 'TRADER'
  join T09_MANDANT t09 on t09.T09_ID = FK_T17_T09_MANDANT;


create or replace force view v03_guard_identity 
as
select distinct lower(t01.t01_nt_id) as nt_id,
       null as start_date,
       null as firstname,
       null as lastname,
       null as last_login_date
  from t01_employee t01, t17_user_role t17
 where t17.fk_t17_t01_employee = t01.t01_id 
   and fk_t17_t29_role <> 'TRADER';


grant select on mgb_prd.v05_guard_profile to mgb_prd_read;
grant select on mgb_prd.v04_guard_previlege to mgb_prd_read;
grant select on mgb_prd.v03_guard_identity to mgb_prd_read;

create or replace force view mgb_prd.v02_guard_export (firstname,
                                                       lastname,
                                                       nt_id,
                                                       mandant,
                                                       role
                                                      )
as
   select t01.t01_firstname as firstname, t01.t01_lastname as lastname,
          t01.t01_nt_id as nt_id, t17.fk_t17_t09_mandant as mandant,
          t17.fk_t17_t29_role as role
     from t01_employee t01, t17_user_role t17
    where t17.fk_t17_t01_employee = t01.t01_id and fk_t17_t29_role <> 'TRADER';


grant select on mgb_prd.v02_guard_export to mgb_prd_read;
