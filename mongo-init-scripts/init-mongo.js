db.createUser({
    user: "rootuser",
    pwd: "rootpass",
    roles: [
        {
            role: "readWrite",
            db: "admin"
        }
    ]
});

db = db.getSiblingDB('productdb');
db.createCollection('products');

db = db.getSiblingDB('orderdb');
db.createCollection('orders');

db = db.getSiblingDB('customerdb');
db.createCollection('customers');
