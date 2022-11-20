import pandas as pd
import requests as req
import xlrd
from bs4 import BeautifulSoup
import pandas as pd
import re
import string
import nltk
import pymorphy2
import itertools
morph = pymorphy2.MorphAnalyzer()

valuta = str(input())
# вводится значение из списка ['Евро','Рубль России','Доллар США','Фунт стерлингов','Китайский Юань','Казахский Тенге','Турецкая лира','Швейцарский франк','Шведская крона',
#                 'Японская иена','Армянский драм','Грузинский лари','ОАЭ Дирхам','Чешская крона','Болгарский лев','Норвежская крона']
link='https://quote.rbc.ru/tag/currency'

all_currencies=['Евро','Рубль России','Доллар США','Фунт стерлингов','Китайский Юань','Казахский Тенге','Турецкая лира','Швейцарский франк','Шведская крона',
                'Японская иена','Армянский драм','Грузинский лари','ОАЭ Дирхам','Чешская крона','Болгарский лев','Норвежская крона']


def variants(currency):
    # разделим название валюты на отдельные слова
    currency = currency.lower().split()
    if currency[0] == 'евро':
        phrases = ['евро', '€']
        return phrases
    shape_of_each = []
    for word in currency:
        p = morph.parse(word)[0]
        shape_of_each.append([x.word for x in p.lexeme])
    combinations = list(itertools.product(*shape_of_each))
    phrases = []
    p = morph.parse(currency[0])[0]
    pp = [x.word for x in p.lexeme]
    k = morph.parse(currency[1])[0]
    kk = [x.word for x in k.lexeme]
    for i in range(len(combinations)):
        phrases.append(combinations[i][0] + ' ' + combinations[i][1])
    phrases += pp
    if currency[1] != 'крона':
        k = morph.parse(currency[1])[0]
        kk = [x.word for x in k.lexeme]
        phrases += kk
    if currency[0] == 'рубль':
        phrases.append('₽')
    if currency[0] == 'доллар':
        phrases.append('$')
    if currency[0] == 'фунт':
        phrases.append('£')
    return phrases

s = list(map(variants, all_currencies))

def find(curr):
    curr=curr.lower()
    for i in s:
        if curr in i:
            return i

spisok_valute = find(valuta)
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

def goParse_1(lik):
    description,names,links = [],[],[]
    res = req.get(lik)
    html = BeautifulSoup(res.text, 'lxml')
    description=html.find_all('span', class_="q-item__description")
    names = html.find_all('span', class_="q-item__title js-rm-central-column-item-text")
    links = html.find_all('a', class_="q-item__link")
    df1=pd.DataFrame()
    for i, name in enumerate(names):
        names[i]=name.text
    for j, des in enumerate(description):
        description[j]=des.text
    df1['название']=names
    df1['описание'] = description
    df1['ссылка']=un(list(re.findall(r'(https?://[^\s]+)', str(links))))
    for i in range(0,len(df1['название'])):
        df1['название'][i] = str(df1['название'][i]).replace("\n", "").lstrip()
        df1['описание'][i] = str(df1['описание'][i]).replace("\n", "").lstrip()
    return df1
df_=goParse_1(link)
def valut(spisok):
    c=[]
    for i in range(0,len(df_)):
        for string_ in spisok_valute:
            if string_ in df_['описание'][i]:
                c.append(df_['ссылка'][i])
    return set(c)
print(valut(spisok_valute))