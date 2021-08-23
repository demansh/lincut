#Core module

`Core` module contains the domain business logic.

It should not depend on exact implementations of infrastructure modules like `api` or `persistance`. But should depend
on interfaces to those modules.
