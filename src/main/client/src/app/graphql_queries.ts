import gql from 'graphql-tag';

export const findAllOrders = gql`
	query {
		findAllOrders {
			id
			status
			dateCreated
			productOrders {
				id
			}
			user {
				id
			}
		}
	}
`;

export const findAllProducts = gql`
	query {
		findAllProducts {
			id
			name
			price
			picture
		}
	}
`;

export const findAllProductOrders = gql`
	query {
		findAllProductOrders {
			id
			order {
				id
				user {
					id
				}
			}
			product {
				id
				name
			}
		}
	}
`;

export const findProductById = gql`
	query($id: ID!) {
		findProductById(id: $id) {
			id
			name
			price
			picture
			details
		}
	}
`;

export const findProductsFromOrdersByProductId = gql`
	query($id: ID!) {
		findProductsFromOrdersByProductId(id: $id) {
			id
			name
			price
			picture
			details
		}
	}
`;

export const newProduct = gql`
	mutation(
		$name: String!
		$price: Float!
		$picture: String!
		$details: String!
	) {
		newProduct(
			name: $name
			price: $price
			picture: $picture
			details: $details
		) {
			id
			name
			price
			picture
			details
		}
	}
`;

export const deleteProduct = gql`
	mutation($id: ID!) {
		deleteProduct(id: $id)
	}
`;

export const findAllProductsFromOrdersByProductId = gql`
	query($id: ID!) {
		findAllProductsFromOrdersByProductId(id: $id) {
			order {
				productOrders {
					product {
						id
						name
						price
						picture
						details
					}
				}
			}
		}
	}
`;

export const editProduct = gql`
	mutation(
		$id: ID!
		$name: String!
		$price: Float!
		$picture: String!
		$details: String!
	) {
		editProduct(
			id: $id
			name: $name
			price: $price
			picture: $picture
			details: $details
		) {
			id
			name
			price
			picture
			details
		}
	}
`;

export const newOrder = gql`
  mutation(
    $productOrders: [NewProductOrderInput!]!
  ) {
    newOrder(productOrders: $productOrders) {
      id
      status
      dateCreated
      productOrders {
        id
      }
      user {
        id
      }
    }
  }
`;
