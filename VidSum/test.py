"""import torch

print(torch.cuda.get_device_name(0))
print(torch.cuda.current_device())
print(torch.cuda.device_count())"""

"""d = {'text': "[\xa0__\xa0] you're going to starve to"}
if "[\xa0__\xa0]" not in d['text']:
    print(d["text"] + " ")
else:
    print(d["text"].replace("[\xa0__\xa0]","yohohohoho")+" ")"""


entry = {'text':'[Music] sucks and[] [] [] we all know that'}   
a = entry["text"].replace('[','').replace(']','')
print(a)
