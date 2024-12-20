#!/bin/bash

ssh -L 8543:localhost:8543 -R 8643:localhost:8643 -p 2222 s333057@helios.cs.ifmo.ru
