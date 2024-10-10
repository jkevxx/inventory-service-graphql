## Inventory Service with GraphQL

## Queries

```graphql
# Get all products
query {
    productList{
        id,
        name,
        category {
            name
        }
    }
}
```

```graphql
# Get product by id
query {
    productById(id : "fa545ca1"){
        name,
        price,
        category {
            name
        }
    }
}
```

```graphql
# Get all categories
query {
    categoryList{
        id,
        name,
        products {
            id,
            name
        }
    }
}
```

```graphql
# Get category by id
query {
    categoryById(id : 3){
        name,
        products {
            name
        }
    }
}
```

---

## Mutations

```graphql
# Create a product
mutation {
    saveProduct(productRequest : {
        name: "P20",
        price: 3400,
        amount: 4,
        categoryId: 1
    }){
        id,
        name,
        price,
        amount,
        category {
            name
        }
    }
}
```

```graphql
# Update a product
mutation {
    updateProduct(
        id: "a0b3f94c",
        productRequest : {
            name: "Monitor 4k",
            price: 6400,
            amount: 41,
            categoryId: 1
        }){
        id,
        name,
        price,
        amount,
        category {
            name
        }
    }
}
```

```graphql
# delete a product
mutation {
    deleteProduct(id: "e226eb0e")
}
```