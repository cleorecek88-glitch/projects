import sys
import random

spam = sys.argv[1]
with open(spam, "rb") as f:
    email = f.read().decode("utf-8", "ignore").lower()

if random.random() < 0.5:
    print("spam")
else:
    print("notspam")