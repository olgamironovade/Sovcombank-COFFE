import pandas as pd
import requests as req
import xlrd
from bs4 import BeautifulSoup
import re
from datetime import datetime, timedelta


link='https://quote.rbc.ru/tag/currency'

def make_date(first_date):
    ru_to_eng_months = {'янв': '01', 'фев':'02', 'мар':'03', 'апр':'04', 'май':'05', 'июн':'06','июл':'07','авг':'08','сен':'09','окт':'10','ноя':'11','дек':'12'}
    numbers=re.compile(r'\d+')
    numb=numbers.findall(first_date)
    months=re.compile(r'[a-zA-Zа-яА-Я]{3}')
    months_one=months.findall(first_date)
    date=datetime(year=int(2022),month=int(ru_to_eng_months[months_one[0]]),day=int(numb[0]),hour=int(numb[1]),minute=int(numb[2]))
    return date

def un(l):
    a=[]
    for i in l:
        i=i.replace('"','').replace('>','').replace(' ','')
        if 'png' not in i and 'jpg' not in i:
            if i not in a:
                a.append(i)
    return a
def un_2(l):
    a=[]
    for i in l:
        i=i.replace('"','').replace('>','').replace(' ','')
        if '810x405_crop' not in i:
            a.append(i)
    return a

def goParse(lik):
    times,names,links, photos = [],[],[],[]
    res = req.get(lik)
    html = BeautifulSoup(res.text, 'lxml')
    times= html.find_all('span', class_="q-item__date__text")
    names = html.find_all('span', class_="q-item__title js-rm-central-column-item-text")
    links = html.find_all('a', class_="q-item__link")
    photos = html.find_all('img', class_="g-image q-item__image js-rm-central-column-item-image")
    df=pd.DataFrame()
    for i, time in enumerate(times):
        times[i]=time.text
    for i, name in enumerate(names):
        names[i]=name.text
    df['время']= times
    df['название']=names
    df['ссылка']=un(list(re.findall(r'(https?://[^\s]+)', str(links))))
    df['фотография']=un_2(list(re.findall(r'(https?://[^\s]+)', str(photos))))
    for i in range(0,len(df['время'])):
        df['время'][i]=str(df['время'][i]).replace(" ","").replace("\n","")
        df['время'][i]=make_date(df['время'][i])
    return df


print(goParse(link))