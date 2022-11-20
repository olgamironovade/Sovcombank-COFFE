from flask_sqlalchemy import SQLAlchemy
from config import create_app

db = SQLAlchemy(create_app())


class UsersInfo(db.Model):
    __tablename__ = "userinfo"

    id = db.Column(
        db.INTEGER,
        primary_key=True,
        doc="Unique id in table",
    )
    name = db.Column(
        db.TEXT,
        nullable=False,
        doc="Username",
    )
    surname = db.Column(
        db.TEXT,
        nullable=False,
        doc="User last name",
    )
    patronymic = db.Column(
        db.TEXT,
        nullable=True,
        doc="User patronymic",
    )
    date_of_birth = db.Column(
        db.TEXT,
        nullable=False,
        doc="age",
    )
    email = db.Column(
        db.VARCHAR(100),
        nullable=False,
        doc="email",
    )
    phone_number = db.Column(
        db.VARCHAR(11),
        nullable=False,
        doc="phone number",
    )
    password = db.Column(
        db.VARCHAR(100),
        nullable=False,
        doc="hex of user password",
    )
    def __init__(self, name, surname, patronymic, date_of_birth, email, phone_number, password):
        self.name = name
        self.surname = surname
        self.patronymic = patronymic
        self.date_of_birth = date_of_birth
        self.email = email
        self.phone_number = phone_number
        self.password = password

    def __repr__(self):
        return f'{self.id}'