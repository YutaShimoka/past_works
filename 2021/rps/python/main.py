#!/usr/bin/env python
hand_list = [0, 1, 2]
text_hand_list = ['グー', 'パー', 'チョキ']


def main():
    import random
    print('最初はグー')
    print('じゃん、けん、ぽん')
    while(True):
        while(True):

            print('', end='\n')  # 空白行

            for i in range(3):
                print(str(i) + ': ' + text_hand_list[i])

            print('', end='\n')  # 空白行

            player_hand = input('=> ')
            if player_hand in list(map(str, hand_list)):
                break
        player_hand = int(player_hand)
        npc_hand = random.choice(hand_list)

        print('', end='\n')  # 空白行

        print('あなたは' + text_hand_list[player_hand] + 'です。')
        print('NPCは' + text_hand_list[npc_hand] + 'です。')

        print('', end='\n')  # 空白行

        if player_hand == npc_hand:
            print('あい、こで、しょ')
        else:
            if (player_hand - npc_hand + 3) % 3 == 1:
                print('勝ち')
            else:
                print('負け')
            break


if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print('\n', end='Bye')
    except EOFError:
        print('', end='Bye')
