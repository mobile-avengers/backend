
build:
	docker-compose build

up:
	docker-compose up

rebuild-up: build up

.PHONY: build up rebuild-up
