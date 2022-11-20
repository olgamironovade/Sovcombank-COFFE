import pandas as pd
import nltk
import numpy as np
import re
from nltk.stem import wordnet  # to perform lemmitization
from sklearn.feature_extraction.text import CountVectorizer  # to perform bow
from sklearn.feature_extraction.text import TfidfVectorizer  # to perform tfidf
from nltk import pos_tag  # for parts of speech
from sklearn.metrics import pairwise_distances  # to perfrom cosine similarity
from nltk import word_tokenize  # to create tokens
from nltk.corpus import stopwords  # for stop words


# nltk.download('punkt')
# nltk.download('wordnet')
# nltk.download('omw-1.4')
# nltk.download('averaged_perceptron_tagger')
# nltk.download('stopwords')


def text_normalization(text):
    text = str(text).lower()  # text to lower case
    spl_char_text = re.sub(r'[^ а-я]', '', text)  # removing special characters
    tokens = nltk.word_tokenize(spl_char_text)  # word tokenizing
    lema = wordnet.WordNetLemmatizer()  # intializing lemmatization
    tags_list = pos_tag(tokens, tagset=None)  # parts of speech
    lema_words = []  # empty list
    for token, pos_token in tags_list:
        if pos_token.startswith('V'):  # Verb
            pos_val = 'v'
        elif pos_token.startswith('J'):  # Adjective
            pos_val = 'a'
        elif pos_token.startswith('R'):  # Adverb
            pos_val = 'r'
        else:
            pos_val = 'n'  # Noun
        lema_token = lema.lemmatize(token, pos_val)  # performing lemmatization
        lema_words.append(lema_token)  # appending the lemmatized token into a list

    return " ".join(lema_words)  # returns the lemmatized tokens as a sentence


def stopword_(text):
    tag_list = pos_tag(nltk.word_tokenize(text), tagset=None)
    stop = stopwords.words('russian')
    lema = wordnet.WordNetLemmatizer()
    lema_word = []
    for token, pos_token in tag_list:
        if token in stop:
            continue
        if pos_token.startswith('V'):
            pos_val = 'v'
        elif pos_token.startswith('J'):
            pos_val = 'a'
        elif pos_token.startswith('R'):
            pos_val = 'r'
        else:
            pos_val = 'n'
        lema_token = lema.lemmatize(token, pos_val)
        lema_word.append(lema_token)
    return " ".join(lema_word)


def chat_tfidf(text):
    df = pd.read_excel('C:/Users/Дмитрий/Desktop/test.xlsx')
    df['lemmatized_text'] = df['Context'].apply(text_normalization)
    lemma=text_normalization(text) # calling the function to perform text normalization
    tfidf = TfidfVectorizer()  # intializing tf-id
    x_tfidf = tfidf.fit_transform(df['lemmatized_text']).toarray()  # transforming the data into array
    tf=tfidf.transform([lemma]).toarray() # applying tf-idf
    df_tfidf = pd.DataFrame(x_tfidf, columns=tfidf.get_feature_names_out())
    cos=1-pairwise_distances(df_tfidf,tf,metric='cosine') # applying cosine similarity
    index_value=cos.argmax() # getting index value
    return df['Text Response'].loc[index_value]
 # applying the fuction to the dataset to get clean text

# defining a function that returns response to query using tf-idf
print(chat_tfidf('У меня не работает перевод средств между счетами. Что делать? '))
