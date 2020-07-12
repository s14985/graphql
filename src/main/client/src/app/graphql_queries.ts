import gql from 'graphql-tag';

export const findAllProducts = gql`
	query {
		findAllProducts {
      __typename
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
		$manufacturer: String!
		$itemCode: String!
		$color: String!
		$material: String!
	) {
		newProduct(
			name: $name
			price: $price
			picture: $picture
			details: $details
			manufacturer: $manufacturer
			itemCode: $itemCode
			color: $color
			material: $material
		) {
      __typename
			id
			name
			price
			picture
			details
			manufacturer
			itemCode
			color
			material
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
		$manufacturer: String!
		$itemCode: String!
		$color: String!
		$material: String!
	) {
		editProduct(
			id: $id
			name: $name
			price: $price
			picture: $picture
			details: $details
			manufacturer: $manufacturer
			itemCode: $itemCode
			color: $color
			material: $material
		) {
      __typename
			id
			name
			price
			picture
			details
			manufacturer
			itemCode
			color
			material
		}
	}
`;

export const newOrder = gql`
	mutation($productOrders: [NewProductOrderInput!]!) {
		newOrder(productOrders: $productOrders) {
      __typename
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
      __typename
			id
			name
			price
			picture
			details
			manufacturer
			itemCode
			color
			material
		}
		findAllProductsFromOrdersByProductId(id: $id) {
			order {
        __typename
        id
				productOrders {
          __typename
          id
					product {
            __typename
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
