# coding: utf-8

import glob
import os.path
from xml.etree import ElementTree
os.chdir('benchmark/build\outputs/androidTest-results\connected')
files = []
for file in glob.glob("*.xml"):
    files.append(file)
    tree = ElementTree.parse(file)
    d = dict()
    for testcase in tree.findall("testcase"):
        d[testcase.get('name')] = testcase.get('time')
    print(d)