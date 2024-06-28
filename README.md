<<<<<<< HEAD
# Calendar

### Projekt Prgramistyczny

Program zarządzania czasem z możliwością tworzenia tasków i eventów.

### Instrukcja uruchomienia

1. Pobrać repozytoria  
   https://github.com/GargamelJR1/Calendar_Frontend  
   https://github.com/GargamelJR1/Calendar_Backend

2. W folderze Calendar_Backend uruchomić terminal i wykonać komendy  
   Uruchomić docker  
   `docker compose -f composeA.yaml build`  
   `docker compose -f composeA.yaml up`  
   Po każdej komendzie odczekać na jej wykonanie

3. W folderze Calendar_Frontend uruchomić nowy terminal i wykonać komendy

   #### Wymagane aplikacje:

   - Node.js https://nodejs.org/en
   - Npm `npm install -g npm`
   - Angular `npm install -g @angular/cli@17`

   #### Uruchamianie

   `npm instll` (Wykonaj tylko za pierwszym razem lub po dodaniu paczki)  
   `ng serve`  
   Po każdej komendzie odczekać na jej wykonanie  
   Strona internetowa uruchomi się pod adresem http://localhost:4200/

W tym momencie aplikacja jest w pełni funkcjonalna.
=======
There are two options to run this project. Both options need installed and working Docker.
1. as normal project in IDE (IntelliJ or sth)
2. run Docker Compose (composeA file or manually created)

file composeA includes database and API, it runs all backend services

Backend docker image is in https://hub.docker.com/repository/docker/plgr229/calendar-backend/general
>>>>>>> 383b13115900ed363bef025e963fc1fee97cecb8
