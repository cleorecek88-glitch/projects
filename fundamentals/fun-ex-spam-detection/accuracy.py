import subprocess
import sys
import os
import random

detector = sys.argv[1]  # path to .py file
path_to_ham = "enron3/ham/"
path_to_spam = "enron3/spam/"

# Get the list of ham and spam emails
ham_emails = random.sample(os.listdir(path_to_ham), 200)
spam_emails = random.sample(os.listdir(path_to_spam), 200)


# Run the spam detector
def is_spam_according_to_detector(detector, path_to_email_file):
    os.system(f"cp {path_to_email_file} email.txt")
    result = subprocess.run(
        [sys.executable, detector, "email.txt"],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE)
    stdout = result.stdout.decode("utf-8", "ignore").strip()
    stderr = result.stderr.decode("utf-8", "ignore").strip()
    os.system("rm email.txt")
    if stdout == "spam":
        return True
    elif stdout == "notspam":
        return False
    else:
        print(f"Invalid result from spam detector: {repr({"stdout": stdout, "stderr": stderr})}\n\nI expected 'spam' or 'notspam'.")
        exit(2)


# Check the accuracy of the spam detector
true_positives = 0
true_negatives = 0
false_positives = 0
false_negatives = 0

for email in ham_emails:
    if is_spam_according_to_detector(detector, path_to_ham + email):
        false_positives += 1
    else:
        true_negatives += 1

for email in spam_emails:
    if is_spam_according_to_detector(detector, path_to_spam + email):
        true_positives += 1
    else:
        false_negatives += 1

percent_true_negatives = true_negatives / len(spam_emails) * 100
percent_true_positives = true_positives / len(ham_emails) * 100

print(f"Your spam detector correctly identified {percent_true_positives}% of spam emails as spam.")
print(f"Your spam detector correctly identified {percent_true_negatives}% of ham emails as not spam.")

if percent_true_negatives < 75 or percent_true_positives < 75:
    exit(1)
else:
    exit(0)