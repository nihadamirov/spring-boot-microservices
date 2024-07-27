// Create the 'productdb' database and a sample collection
db = db.getSiblingDB('productdb');
db.createCollection('products');

// Create the 'customerdb' database and a sample collection
db = db.getSiblingDB('customerdb');
db.createCollection('customers');

// Create the 'orderdb' database and a sample collection
db = db.getSiblingDB('orderdb');
db.createCollection('orders');
