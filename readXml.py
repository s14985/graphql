import xml.etree.ElementTree as ET
import xlsxwriter
import os
import csv

path = 'E:\\Skróty\\KOR\\Studia\\Praca_inz\\apache-jmeter-5.3\\bin\\output'
workbook = xlsxwriter.Workbook('C:\\Users\\Cross\\Desktop\\xlsx\\responses_total.xlsx')
csvfile = 'C:\\Users\\Cross\\Desktop\\xlsx\\summary_report.csv'

for filename in os.listdir(path):
    if not filename.endswith('.xml'): continue
    fullname = os.path.join(path, filename)
    root = ET.parse(fullname)
    worksheet = workbook.add_worksheet(filename[:-4])

    lb	= ['Label']
    lt	= ['Latency (ms)']
    na	= ['Number of active threads for all thread groups']
    ng	= ['Number of active threads in this group']
    rc	= ['Response Code']

    for http in root.findall('httpSample'):
        lt.append(http.get('lt'))
        lb.append(http.get('lb'))
        rc.append(http.get('rc'))
        ng.append(http.get('ng'))
        na.append(http.get('na'))

    array = [lt, lb, rc, ng, na]

    for col, data in enumerate(array):
        worksheet.write_column(0, col, data)

worksheet = workbook.add_worksheet('summary_report')
with open(csvfile, 'rt') as f:
    reader = csv.reader(f)
    for r, row in enumerate(reader):
        for c, col in enumerate(row):
            worksheet.write(r, c, col)

workbook.close()

for filename in os.listdir(path):
    if filename.endswith('.xml'):
        fullname = os.path.join(path, filename)
        os.remove(fullname)

os.remove(csvfile)
os.remove('E:\\Skróty\\KOR\\Studia\\Praca_inz\\apache-jmeter-5.3\\bin\\result.jtl')