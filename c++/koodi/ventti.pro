TEMPLATE = app
CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += \
        kasi.cpp \
        kortti.cpp \
        main.cpp \
        pakka.cpp \
        pelaaja.cpp

HEADERS += \
    kasi.h \
    kortti.h \
    pakka.h \
    pelaaja.h

DISTFILES += \
    ReadME.txt
