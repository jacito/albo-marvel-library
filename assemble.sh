mkdir albo
cd albo
git clone --branch  master https://github.com/jacito/albo-marvel-library.git
cd albo-marvel-library/Services/target/
sudo jar cvf marvel.war .
cp marvel.war ../../../marvel.war
