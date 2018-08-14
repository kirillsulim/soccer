## Description

Create team group stage for soccer world championship.
Team are formed in groups by 4 commands.
There are 8 groups (A-H)
Each team plays with each in group.

Create REST application (using Spring Framework), implementing CRUD operations on entities:
- Group
- Team
- Game

Unit tests and validation are not obligate but will be good.

Additional:
Show current group state sorted by points

|Team    |Win|Draw |Loose|Scored/Missed|Points|
|---     |---|---  |---  |---          |   ---|
|Russia  |  1|   0 |  1  |  2/2        |    3 |
|Germany |  2|   0 |  0  |  3/1        |    6 |
|Japan   |  0|   0 |  2  |  0/2        |    0 |

### Rules
Win = 3 scores
Draw = 1 score

Table must be shown as JSON array or object

As a persistence layer use MongoDB with Spring Data connection.
Kotlin as a implementation language is more preferable (I start with Java).
Must use Maven as build tool.