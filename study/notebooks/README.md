
pyenv-virtualenv

https://github.com/yyuu/pyenv-virtualenv

설치법
  $ git clone https://github.com/yyuu/pyenv-virtualenv.git ~/.pyenv/plugins/pyenv-virtualenv
  $ echo 'eval "$(pyenv virtualenv-init -)"' >> ~/.bash_profile

가상 환경 생성하기
  $ pyenv virtualenv <version> <name>
  $ pyenv virtualenv 2.7.10 my-virtual-env-2.7.10

생성된 가상 환경 리스트 보기
  $ pyenv virtualenvs

가상 환경 활성화, 비활성화
  $ pyenv activate <name>
  $ pyenv deactivate

가상 환경 삭제
  $ pyenv uninstall <name>

## 의존성 관리하기

라이브러리 관리 파일 생성하기
  $ pip freeze > requirements.txt

라이브러리 관리 파일로부터 설치하기
  $ pip install -r requirements.txt

