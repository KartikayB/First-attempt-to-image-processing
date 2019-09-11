"""
main.py

Example use of image_comp on samples provided
,
Nicholas Bochenski & Franciszek Madej
"""
import numpy as np
from PIL import Image

from image_comp import color_line, load_img, min_variance, perpendicular

filename = 'saved' + '.jpg'
img = load_img(filename)
best_perp_data = perpendicular(img, *min_variance(img))
color_lines = best_perp_data

#x = np.array(color_line(img, *best_perp_data))
x = Image.open('saved.jpg', 'r')
pix_x = list(x.getdata())
#max_diff = (255**2) / len(x)
ar=[]
flag=0
for j_a in range (0,10):
    gram_mean_sum=0.0
    for i in range(1,205):
        filename = 'samples/Dataset/' +str(j_a)+"/" +str(i) + '.JPG'
        gram_mat_X = []
        gram_mat_Y = []
        img = load_img(filename)
        #y = list(np.array(color_line(img, *best_perp_data)))
        y = Image.open(filename, 'r')
        pix_y = list(y.getdata())
        for i in range(100):
            gram_mat_row = []
            for i in range(100):
                gram_mat_row.append(pix_x[i] * pix_x[i])
            gram_mat_X.append(gram_mat_row)
        for i in range(100):
            gram_mat_row = []
            for i in range(100):
                gram_mat_row.append(pix_y[i] * pix_y[i])
            gram_mat_Y.append(gram_mat_row)
        input_gram = [[gram_mat_X[j][i] for j in range(len(gram_mat_X))] for i in range(len(gram_mat_X[0]))]
        target_gram = [[gram_mat_Y[j][i] for j in range(len(gram_mat_Y))] for i in range(len(gram_mat_Y[0]))]
        for i, row in enumerate(input_gram):
            for j, num in enumerate(row):
                input_gram[i][j] = num/10000
        for i, row in enumerate(target_gram):
            for j, num in enumerate(row):
                target_gram[i][j] = num/10000
        gram_sum = 0
        for i in range(100):
            for j in range(100):
                gram_sum += (input_gram[i][j] - target_gram[i][j])**2
        gram_mean = gram_sum/10000
        #diff = x - y
        #diff_sq = diff ** 2
        #total_diff = np.mean(diff_sq)
        #percentage_diff = str((1 - total_diff / max_diff) * 100) + "%"
        #sum=sum+((1 - total_diff / max_diff) * 100)
        gram_mean_sum += gram_mean

    print(gram_mean_sum/205)

    ar.append(gram_mean_sum/205)
f = []
for i in range(len(ar)):
    f.append(float(ar[i]))

print("Final =",f.index(max(f)))