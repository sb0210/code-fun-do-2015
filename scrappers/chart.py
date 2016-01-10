import matplotlib.pyplot as plt
import numpy as np
# Data to plot
for i in range(0,25):	
	labels = 'Positive', 'Negative', 'Neutral'
	pos = np.random.randint(1,50)
	neg = np.random.randint(1,50)
	nuet = 100 - pos - neg
	sizes = [pos, neg, nuet]
	colors = ['yellowgreen', 'lightcoral', 'lightskyblue']
	explode = (0, 0.1, 0)  # explode 1st slice
	# Plot
	plt.pie(sizes, explode=explode, labels=labels, colors=colors,
	        autopct='%1.1f%%', shadow=True, startangle=140)
	plt.axis('equal')
	plt.savefig('test' + str(i) + '.png')
	plt.clf()
# fig.savefig('foo.png')