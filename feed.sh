#!/bin/bash

ruby scripts/feeder.rb | mysql -ubookish-admin -p bookish
