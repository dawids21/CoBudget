# Models

## Users
Every document is one user

### User
- id
- username
- password

## UserConfigurations
Every document is a configuration for specific user

### UserConfiguration
- user id
- accounts names
- array of earnings category's names
- array of expenses categories
- main account for expenses
- notification for expense adding
- rest...

### Configuration > expense category
- name
- array of subcategory's names

## Expenses
Every document is one month for specific user

### Expenses
- user id
- date
- array of expenses
- sum of expenses
- array of categories

### Expenses > date
- year
- month

### Expenses > expense
- day
- amount
- category
- subcategory

### Expenses > categories
- name
- array of subcategories
- sum of expenses

### Expenses > categories > subcategories
- name
- sum of expenses

## Earnings
Every document is one month for specific user

### Earnings
- user id
- date
- array of earnings
- sum of earnings
- array of categories

### Expenses > date
- year
- month

### Earnings > earning
- day
- amount
- category

### Earnings > categories
- name
- sum of earnings

## Plans
Every document is a one-month plan for specific user

### Plan
- user id
- date
- array of earnings' categories
- array of expenses' categories
- sum of planned earnings
- sum of planned expenses

### Plan > date
- year
- month

### Plan > expense category
- name
- sum of planned expenses
- array of subcategories

### Plan > expense category > subcategory
- name
- planned expenses

### Plan > earning category
- name
- sum of planned earnings

## Accounts
Every document is a state of one account for each user for a given year

### Account
- user id
- name
- amount of money
- year
- previous states for past months

### Account > state
- month
- amount

// Just idea to verify
## Current month data
Every document has data of the given user for current month

### Current month data
- user id
- month
- year
- earnings
- expenses
- plan

### Current month data > earnings
- array of earnings
- sum of earnings
- array of categories

### Current month data > earnings > earning
- day
- amount
- category

### Current month data > earnings > category
- name
- sum of earnings

### Current month data > expenses
- array of expenses
- sum of expenses
- array of categories

### Current month data > expenses > expense
- day
- amount
- category
- subcategory

### Current month data > expenses > categories
- name
- array of subcategories
- sum of expenses

### Current month data > expenses > categories > podcategories
- name
- sum of expenses

### Current month data > plan
- array of earnings's categories
- array of expense's categories
- planned earnings
- planned expenses

### Current month data > plan > category of expenses
- name
- sum of planned expenses
- array of subcategories

### Current month data > plan > category of expenses > subcategory
- name
- planned expenses

### Current month data > plan > category of earnings
- name
- sum of planned earnings
