package com.andor.watchit.helper

import com.andor.watchit.network.schema.TopRatedMovieSchema
import com.google.gson.Gson

object TestData {

    const val SERVER_RESPONSE_JSON_SUCCESS: String = "{\n" +
            "    \"page\": 1,\n" +
            "    \"total_results\": 10000,\n" +
            "    \"total_pages\": 500,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"popularity\": 559.414,\n" +
            "            \"id\": 419704,\n" +
            "            \"video\": false,\n" +
            "            \"vote_count\": 1843,\n" +
            "            \"vote_average\": 6,\n" +
            "            \"title\": \"Ad Astra\",\n" +
            "            \"release_date\": \"2019-09-17\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Ad Astra\",\n" +
            "            \"genre_ids\": [\n" +
            "                878,\n" +
            "                18,\n" +
            "                53,\n" +
            "                12,\n" +
            "                9648\n" +
            "            ],\n" +
            "            \"backdrop_path\": \"/5BwqwxMEjeFtdknRV792Svo0K1v.jpg\",\n" +
            "            \"adult\": false,\n" +
            "            \"overview\": \"The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.\",\n" +
            "            \"poster_path\": \"/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 263.567,\n" +
            "            \"vote_count\": 2405,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/db32LaOibwEliAmSL2jjDF6oDdj.jpg\",\n" +
            "            \"id\": 181812,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/jOzrELAzFxtMx2I4uDGHOotdfsS.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Star Wars: The Rise of Skywalker\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                12,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"Star Wars: The Rise of Skywalker\",\n" +
            "            \"vote_average\": 6.6,\n" +
            "            \"overview\": \"The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.\",\n" +
            "            \"release_date\": \"2019-12-18\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 271.218,\n" +
            "            \"vote_count\": 7814,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg\",\n" +
            "            \"id\": 475557,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Joker\",\n" +
            "            \"genre_ids\": [\n" +
            "                80,\n" +
            "                18,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Joker\",\n" +
            "            \"vote_average\": 8.3,\n" +
            "            \"overview\": \"During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.\",\n" +
            "            \"release_date\": \"2019-10-02\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 218.027,\n" +
            "            \"vote_count\": 1064,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/jyw8VKYEiM1UDzPB7NsisUgBeJ8.jpg\",\n" +
            "            \"id\": 512200,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/zTxHf9iIOCqRbxvl8W5QYKrsMLq.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Jumanji: The Next Level\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                12,\n" +
            "                35,\n" +
            "                14\n" +
            "            ],\n" +
            "            \"title\": \"Jumanji: The Next Level\",\n" +
            "            \"vote_average\": 6.7,\n" +
            "            \"overview\": \"As the gang return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to brave parts unknown and unexplored in order to escape the world’s most dangerous game.\",\n" +
            "            \"release_date\": \"2019-12-04\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 198.264,\n" +
            "            \"vote_count\": 2414,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg\",\n" +
            "            \"id\": 496243,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg\",\n" +
            "            \"original_language\": \"ko\",\n" +
            "            \"original_title\": \"기생충\",\n" +
            "            \"genre_ids\": [\n" +
            "                35,\n" +
            "                18,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Parasite\",\n" +
            "            \"vote_average\": 8.6,\n" +
            "            \"overview\": \"All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.\",\n" +
            "            \"release_date\": \"2019-05-30\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 147.802,\n" +
            "            \"vote_count\": 1426,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/vloNTScJ3w7jwNwtNGoG8DbTThv.jpg\",\n" +
            "            \"id\": 420809,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/skvI4rYFrKXS73BJxWGH54Omlvv.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Maleficent: Mistress of Evil\",\n" +
            "            \"genre_ids\": [\n" +
            "                12,\n" +
            "                14,\n" +
            "                10751\n" +
            "            ],\n" +
            "            \"title\": \"Maleficent: Mistress of Evil\",\n" +
            "            \"vote_average\": 7.2,\n" +
            "            \"overview\": \"Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play.\",\n" +
            "            \"release_date\": \"2019-10-16\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 190.082,\n" +
            "            \"vote_count\": 145,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/yJdeWaVXa2se9agI6B4mQunVYkB.jpg\",\n" +
            "            \"id\": 449924,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/ekP6EVxL81lZ4ivcqPsoZ72rY0h.jpg\",\n" +
            "            \"original_language\": \"cn\",\n" +
            "            \"original_title\": \"葉問4\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                18,\n" +
            "                36\n" +
            "            ],\n" +
            "            \"title\": \"Ip Man 4: The Finale\",\n" +
            "            \"vote_average\": 6,\n" +
            "            \"overview\": \"Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son.\",\n" +
            "            \"release_date\": \"2019-12-20\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 159.66,\n" +
            "            \"vote_count\": 207,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/AuGiPiGMYMkSosOJ3BQjDEAiwtO.jpg\",\n" +
            "            \"id\": 530915,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/tUWivz05fcY14K6RzicRm7IHkUD.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"1917\",\n" +
            "            \"genre_ids\": [\n" +
            "                18,\n" +
            "                36,\n" +
            "                10752\n" +
            "            ],\n" +
            "            \"title\": \"1917\",\n" +
            "            \"vote_average\": 8,\n" +
            "            \"overview\": \"At the height of the First World War, two young British soldiers, Schofield and Blake are given a seemingly impossible mission. In a race against time, they must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers—Blake's own brother among them.\",\n" +
            "            \"release_date\": \"2019-12-10\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 131.038,\n" +
            "            \"vote_count\": 1771,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/pjeMs3yqRmFL3giJy4PMXWZTTPa.jpg\",\n" +
            "            \"id\": 330457,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Frozen II\",\n" +
            "            \"genre_ids\": [\n" +
            "                12,\n" +
            "                16,\n" +
            "                10402,\n" +
            "                10751\n" +
            "            ],\n" +
            "            \"title\": \"Frozen II\",\n" +
            "            \"vote_average\": 7,\n" +
            "            \"overview\": \"Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.\",\n" +
            "            \"release_date\": \"2019-11-20\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 123.871,\n" +
            "            \"vote_count\": 47,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/eU0orGizEpOli4wtN8HtfOOJDlA.jpg\",\n" +
            "            \"id\": 516700,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/mlaX4c2M6usnhXHG5sYJcKVXflA.jpg\",\n" +
            "            \"original_language\": \"id\",\n" +
            "            \"original_title\": \"Gundala\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                80,\n" +
            "                18\n" +
            "            ],\n" +
            "            \"title\": \"Gundala\",\n" +
            "            \"vote_average\": 5.3,\n" +
            "            \"overview\": \"Sancaka has lived on the streets since his parents left him. Living a hard life, Sancaka survives by thinking about his own safety. When the condition of the city gets worse and injustice rages throughout the country, Sancaka must decide whether he continues to live to look after himself or rise to become their oppressed hero.\",\n" +
            "            \"release_date\": \"2019-08-29\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 104.551,\n" +
            "            \"vote_count\": 3946,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg\",\n" +
            "            \"id\": 466272,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/yB2hTgz9CTVYjlMWPSl3LPx5nWj.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Once Upon a Time… in Hollywood\",\n" +
            "            \"genre_ids\": [\n" +
            "                35,\n" +
            "                18,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Once Upon a Time… in Hollywood\",\n" +
            "            \"vote_average\": 7.5,\n" +
            "            \"overview\": \"Los Angeles, 1969. TV star Rick Dalton, a struggling actor specializing in westerns, and stuntman Cliff Booth, his best friend, try to survive in a constantly changing movie industry. Dalton is the neighbor of the young and promising actress and model Sharon Tate, who has just married the prestigious Polish director Roman Polanski…\",\n" +
            "            \"release_date\": \"2019-07-25\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 76.663,\n" +
            "            \"vote_count\": 1120,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/uTALxjQU8e1lhmNjP9nnJ3t2pRU.jpg\",\n" +
            "            \"id\": 453405,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/sfW7GcOuwZFuCxVoU5ULlkiDJ7Q.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Gemini Man\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Gemini Man\",\n" +
            "            \"vote_average\": 5.8,\n" +
            "            \"overview\": \"Ageing assassin, Henry Brogen tries to get out of the business but finds himself in the ultimate battle—fighting his own clone who is 25 years younger than him, and at the peak of his abilities.\",\n" +
            "            \"release_date\": \"2019-10-02\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 154.403,\n" +
            "            \"vote_count\": 322,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/mSmiB8XjUnR1GSIljuCPGsk0cwX.jpg\",\n" +
            "            \"id\": 331482,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/3uTxPIdVEXxHpsHOHdJC24QebBV.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Little Women\",\n" +
            "            \"genre_ids\": [\n" +
            "                18,\n" +
            "                10749\n" +
            "            ],\n" +
            "            \"title\": \"Little Women\",\n" +
            "            \"vote_average\": 8.3,\n" +
            "            \"overview\": \"Four sisters come of age in America in the aftermath of the Civil War.\",\n" +
            "            \"release_date\": \"2019-12-25\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 93.826,\n" +
            "            \"vote_count\": 1082,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/dtRbVsUb5O12WWO54SRpiMtHKC0.jpg\",\n" +
            "            \"id\": 338967,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/3ghImmHdp4RnC3UkL6hpLayclnb.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Zombieland: Double Tap\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                35,\n" +
            "                27\n" +
            "            ],\n" +
            "            \"title\": \"Zombieland: Double Tap\",\n" +
            "            \"vote_average\": 7,\n" +
            "            \"overview\": \"Columbus, Tallahassee, Wichita, and Little Rock move to the American heartland as they face off against evolved zombies, fellow survivors, and the growing pains of the snarky makeshift family.\",\n" +
            "            \"release_date\": \"2019-10-09\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 91.439,\n" +
            "            \"vote_count\": 66,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/jlHL2BH176JApGiLnNQLQgdjMFd.jpg\",\n" +
            "            \"id\": 443791,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/fYPiQewg7ogbzro2XcCTACSB2KC.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Underwater\",\n" +
            "            \"genre_ids\": [\n" +
            "                27,\n" +
            "                878,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Underwater\",\n" +
            "            \"vote_average\": 5.8,\n" +
            "            \"overview\": \"After an earthquake destroys their underwater station, six researchers must navigate two miles along the dangerous, unknown depths of the ocean floor to make it to safety in a race against time.\",\n" +
            "            \"release_date\": \"2020-01-08\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 75.387,\n" +
            "            \"vote_count\": 1438,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/pThyQovXQrw2m0s9x82twj48Jq4.jpg\",\n" +
            "            \"id\": 546554,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/cjTQSwcsfVdirSFSHNBXRGkxmWa.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Knives Out\",\n" +
            "            \"genre_ids\": [\n" +
            "                35,\n" +
            "                80,\n" +
            "                18,\n" +
            "                9648,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Knives Out\",\n" +
            "            \"vote_average\": 7.8,\n" +
            "            \"overview\": \"When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.\",\n" +
            "            \"release_date\": \"2019-11-27\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 92.952,\n" +
            "            \"vote_count\": 329,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/7GsM4mtM0worCtIVeiQt28HieeN.jpg\",\n" +
            "            \"id\": 515001,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/agoBZfL1q5G79SD0npArSlJn8BH.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Jojo Rabbit\",\n" +
            "            \"genre_ids\": [\n" +
            "                35,\n" +
            "                18,\n" +
            "                10752\n" +
            "            ],\n" +
            "            \"title\": \"Jojo Rabbit\",\n" +
            "            \"vote_average\": 7.9,\n" +
            "            \"overview\": \"A World War II satire that follows a lonely German boy whose world view is turned upside down when he discovers his single mother is hiding a young Jewish girl in their attic. Aided only by his idiotic imaginary friend, Adolf Hitler, Jojo must confront his blind nationalism.\",\n" +
            "            \"release_date\": \"2019-10-18\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 68.771,\n" +
            "            \"vote_count\": 370,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/u50NAzMDBxTBP0CwmuUmBUkxW05.jpg\",\n" +
            "            \"id\": 593402,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/5QuRbno7OLgXi9bz5YXKrtn3vFH.jpg\",\n" +
            "            \"original_language\": \"it\",\n" +
            "            \"original_title\": \"Tolo Tolo\",\n" +
            "            \"genre_ids\": [\n" +
            "                35\n" +
            "            ],\n" +
            "            \"title\": \"Tolo Tolo\",\n" +
            "            \"vote_average\": 6.5,\n" +
            "            \"overview\": \"Checco is a young Apulian entrepreneur dreamer who has opened a sushi restaurant in his Apulia. However, after one month, the restaurant went bankrupt and he chose to emigrate to Africa to escape from debt. Here he adapts to being a waiter in a resort in Kenya, but at the outbreak of a civil war he decides to embark on a stowaway trip on a boat for migrants to Europe and chooses to do it with his African friends. However, he would not like to return to Italy, but rather to go to Liechtenstein where banking secrecy is in force and there is a lower tax burden than in Italy.\",\n" +
            "            \"release_date\": \"2020-01-01\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 98.206,\n" +
            "            \"vote_count\": 1674,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/pZekG6xabTmZxjmYw10wN84Hp8d.jpg\",\n" +
            "            \"id\": 492188,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/nwoDC0IJA47wr3znJgT1kkk64Gy.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Marriage Story\",\n" +
            "            \"genre_ids\": [\n" +
            "                18\n" +
            "            ],\n" +
            "            \"title\": \"Marriage Story\",\n" +
            "            \"vote_average\": 8,\n" +
            "            \"overview\": \"A stage director and an actress struggle through a grueling, coast-to-coast divorce that pushes them to their personal extremes.\",\n" +
            "            \"release_date\": \"2019-11-06\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 76.77,\n" +
            "            \"vote_count\": 79,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"/MBiKqTsouYqAACLYNDadsjhhC0.jpg\",\n" +
            "            \"id\": 486589,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/bga3i5jcejBekr2FCGJga1fYCh.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Red Shoes and the Seven Dwarfs\",\n" +
            "            \"genre_ids\": [\n" +
            "                16,\n" +
            "                10749\n" +
            "            ],\n" +
            "            \"title\": \"Red Shoes and the Seven Dwarfs\",\n" +
            "            \"vote_average\": 6.6,\n" +
            "            \"overview\": \"Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.\",\n" +
            "            \"release_date\": \"2019-07-25\"\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    val SERVER_RESPONSE_WEATHER_SCHEMA: TopRatedMovieSchema =
        Gson().fromJson<TopRatedMovieSchema>(
            SERVER_RESPONSE_JSON_SUCCESS,
            TopRatedMovieSchema::class.java
        )
}
