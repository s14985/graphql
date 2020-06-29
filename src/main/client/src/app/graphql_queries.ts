import gql from 'graphql-tag';

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
	mutation($productOrders: [NewProductOrderInput!]!) {
		newOrder(productOrders: $productOrders) {
			id
			status
			dateCreated
			productOrders {
				id
			}
		}
	}
`;

export const getProductDetails = gql`
	query($id: ID!) {
		findProductById(id: $id) {
			id
			name
			price
			picture
			details
		}
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

export const finishOrder = gql`
	mutation($id: ID!) {
		finishOrder(id: $id) {
			id
			status
		}
	}
`;
