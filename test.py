import requests


print(requests.post(url='http://127.0.0.1:5000/sign-up', json={'name': '1',
                                                              'surname': '2',
                                                              'patronymic': '3',
                                                              'date_of_birth': '2011-11-11',
                                                              'email': 'pavel@bk.ru',
                                                              'phone_number': '567',
                                                              'password': 'kekokek'
                                                            }).text)

print(requests.post(url='http://127.0.0.1:5000/auth', json={'username': '567', 'password': 'kekokek'}).text)