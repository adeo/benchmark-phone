# coding: utf-8

import glob
import os.path
from xml.etree import ElementTree
import requests


def sendEvent(file):
    params = dict()
    params['token_auth'] = token
    tree = ElementTree.parse(file)
    params["rec"] = "1"
    params["idsite"] = "1"
    params["dimension1"] = tree.find(".//property[@name='device']").get("value")
    params["e_c"] = "session"
    params["e_a"] = "benchmark"
    for testcase in tree.findall("testcase"):
        params["e_n"] = testcase.get('name')
        params["e_v"] = testcase.get('time')
        print(params)
        requests.get(base_url, params=params)


def reportBenchmark():
    os.chdir('benchmark/build/outputs/androidTest-results/connected')
    for file in glob.glob("*.xml"):
        sendEvent(file)




base_url = "http://benchmark-analytics-open.apps.op.acp.adeo.com/matomo.php"
token = "0b44547f5f0c9d526b46247c65aeef57"
reportBenchmark()

