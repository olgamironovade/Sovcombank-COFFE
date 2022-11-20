from flask import request, jsonify
from flask_sqlalchemy import SQLAlchemy

from config import create_app
from model import UsersInfo
from utils.password import hash_password, validate_password, get_random_string


app = create_app()
db = SQLAlchemy(app)


# creating the rest endpoints

# index
# url = http://127.0.0.1:5000/
@app.route('/', methods=['GET'])
def index():
    return {'message': 'Hello world!'}


@app.route('/sign-up', methods=['POST'])
def save_user():
    if request.is_json and request.method == 'POST':
        data = request.get_json()
        salt = get_random_string()
        hashed_password = hash_password(data['password'], salt)
        new_user = UsersInfo(surname=data['surname'],
                             name=data['name'],
                             patronymic=data['patronymic'],
                             date_of_birth=data['date_of_birth'],
                             email=data['email'],
                             phone_number=data['phone_number'],
                             password=f"{salt}${hashed_password}")
        db.session.add(new_user) # adding new data
        db.session.commit()  # persisting the data
        db.session.refresh(new_user)  # refresh the inserted object to get the primary key
        return {'message': f'User {new_user.id} saved successfully', 'code': 200}  # returning response
    else:
        return {'message': 'Payload is incorrect', 'code': 404}

@app.route('/auth', methods=['POST'])
def auth():
    if request.is_json and request.method == 'POST':
        data = request.get_json()
        user = db.session.query(UsersInfo).filter(UsersInfo.email == data['username'] or UsersInfo.phone_number == data['username']).first()
        if validate_password(data['password'], user.password):
            return {'message': f'Access token', 'code': 200}  # returning response
        else:
            return {'message': 'Payload is incorrect', 'code': 404}
    else:
        return {'message': 'Payload is incorrect', 'code': 404}




# driver code
if __name__ == '__main__':
    app.run(debug=False)
