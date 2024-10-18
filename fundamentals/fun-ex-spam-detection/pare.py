import os
import random

spam_dir = "enron3/spam"
ham_dir = "enron3/ham"

spam_files = os.listdir(spam_dir)
ham_files = os.listdir(ham_dir)

spam_keep = random.sample(spam_files, 200)
ham_keep = random.sample(ham_files, 200)

for file in spam_files:
    if file not in spam_keep:
        os.remove(os.path.join(spam_dir, file))
    
for file in ham_files:
    if file not in ham_keep:
        os.remove(os.path.join(ham_dir, file))