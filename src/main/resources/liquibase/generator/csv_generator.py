import csv
from datetime import datetime
import random

now = datetime.now()
lorem = 'Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'

def generate_products_csv():
    with open('../csv/products.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile, delimiter=',', quoting=csv.QUOTE_MINIMAL)

        headers = 'id,name,price,picture,details,manufacturer,item_code,color,material'
        writer.writerow(headers.split(','))

        for i in range(100000):
            id = i + 1
            random_100 = random.randint(1, 100)
            random_price = random.randint(100, 10000)/100
            writer.writerow([id, 'product ' + str(id), random_price, 'assets/images/no-image.gif', lorem, 'manufacturer ' + str(random_100), 'ITM ' + str(id), 'color ' + str(random_100), 'material ' + str(random_100)])

def generate_orders_csv():
    with open('../csv/orders.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile, delimiter=',', quoting=csv.QUOTE_MINIMAL)

        headers = 'id,date_created,status'
        writer.writerow(headers.split(','))

        for i in range(50000):
            id = i + 1
            random_bool = random.randint(0, 1)
            writer.writerow([id, now, random_bool])

def generate_product_orders_csv():
    with open('../csv/product_orders.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile, delimiter=',', quoting=csv.QUOTE_MINIMAL)

        headers = 'id,order_id,product_id,quantity'
        writer.writerow(headers.split(','))

        for i in range(300000):
            id = i + 1
            if id <= 50000:
                order_id = id
            else:
                order_id = random.randint(1, 50000)
            product_id = random.randint(1, 100000)
            random_10 = random.randint(1, 10)
            writer.writerow([id, order_id, product_id, random_10])

if __name__ == "__main__":
    generate_products_csv()
    generate_orders_csv()
    generate_product_orders_csv()