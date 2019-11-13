select 'update T04_TRADE_HISTORIE set FK_T04_T01_CREATED_BY=' || (SELECT T01_ID FROM T01_EMPLOYEE emp WHERE emp.T01_NT_ID=hist.T04_CREATED_BY and FK_T01_T02_MANDANT=trade.FK_T02_T09_MANDANT) || ' where T04_ID=' || T04_ID || ';'
as update_trade_historie
from T04_TRADE_HISTORIE  hist, T02_TRADE trade where hist.FK_T04_T01_CREATED_BY is null and hist.FK_T04_T02_TRADE =trade.T02_ID and
exists (SELECT T01_ID as id FROM T01_EMPLOYEE emp WHERE  emp.T01_NT_ID=hist.T04_CREATED_BY and FK_T01_T02_MANDANT=trade.FK_T02_T09_MANDANT);
