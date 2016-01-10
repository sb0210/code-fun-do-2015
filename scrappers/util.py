import csv

FILENAME_1 = "../app/1politics.csv"
FILENAME_2 = "../app/1news.csv"
FILENAME_3 = "../app/1gadget.csv"
FILENAME_4 = "../app/1india.csv"

names = [FILENAME_1,FILENAME_2,FILENAME_3,FILENAME_4]

for name in names:
	with open(name, 'rb') as csvfile:
		spamreader = csv.reader(csvfile)
		print "{",
		i=0
		for row in spamreader:
			if(i!=len(spamreader)-1):
				print '\"'+row[1] + '\",'
			else:
				print '\"'+row[1] + '\",'
		print "}"
		print("....................\n")

