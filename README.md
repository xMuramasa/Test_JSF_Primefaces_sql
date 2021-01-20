# Test_JSF_Primefaces_sql

### MODELO

#### Campos BD
 - customer_id  long not null
 - name         varchar(64)
 - email        varchar(128)
 - gender       varchar(64)
 - created_date timpestamp
 - age         integer
 - income      integer

- *BD*: testtable
- *tabla*: customer2

```sql
CREATE TABLE IF NOT EXISTS `testtable`.`customer2` (
    `CUSTOMER_ID` Integer NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(64) NOT NULL,
    `EMAIL` VARCHAR(128) NOT NULL,
    `GENDER` VARCHAR(64) NOT NULL,
    `CREATED_DATE` timestamp,
    `AGE` INTEGER,
    `INCOME` INTEGER,
    PRIMARY_KEY(`CUSTOMER_ID`)
);
```


