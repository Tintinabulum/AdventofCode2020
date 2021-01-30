#include <fstream>
#include <iostream>
#include <vector>
#include <utility>
#include <string>
using namespace std;
string mask;
vector<long long int> all(long long int);
long long int base2(int);
vector<string> help(string, int, int);
int main(){
	ifstream input("Input14.txt");
	vector<pair<long long int, long long int>> mem = vector<pair<long long int,long long int>>();
	if (!input.is_open()) return 7;
	string line;
	while (getline(input, line)) {
		if (line.find("mask") == 0) mask = line.substr(line.find("=") + 2);
		else {
			vector<long long int> address = all(stoll(line.substr(line.find("[") + 1, line.find("]"))));
			long long int value = stoll(line.substr(line.find("=") + 2));
			for (long long int add : address) {
				bool found = false;
				for (int i = 0; i < mem.size(); i++)
					if (add == mem.at(i).first) {
						mem.at(i).second = value;
						found = true;
						break;
					}
				if (!found)
					mem.push_back(pair<long long int, long long int>(add, value));
			}
		}
	}
	unsigned long long int total = 0;
	for (pair<long long int, long long int> in : mem) {
		total += in.second;
	}
	cout << total;

}
vector<long long int> all(long long int address) {
	string add = "";
	for (int pow = 0; pow < 36; pow++)
		if ((address / base2(pow)) & 1 == 1) add = "1" + add;
		else add = "0" + add;
	int numberX = 0;
	string debug = mask;
	for (int i = 0; i < mask.size(); i++)
		if (mask.at(i) == 'X') {
			if(i<add.size()-1) add = add.substr(0,i) + 'X' + add.substr(i + 1);
			else add = add.substr(0,add.size() - 1) + 'X';
			numberX++;
		}else if (mask.at(i) == '1')
			if (i < add.size() - 1) add = add.substr(0,i) + '1' + add.substr(i + 1);
			else add = add.substr(0,add.size() - 1) + 'X';
	int size = base2(numberX);
	vector<string> comb = help(add, 0, size);
	vector<long long int> all = vector<long long int>();
	for (int i = 0; i < comb.size(); i++) {
		long long int temp = 0;
		for (int j = 0; j < comb.at(i).size(); j++) {
			temp = temp << 1;
			if (comb.at(i).at(j) == '1') temp = temp | 1;
		}
		all.push_back(temp);
	}
	return all;
}
long long int base2(int pow) {
	long long int base = 1;
	for (int i = 0; i < pow; i++) base *= 2;
	return base;
}
vector<string> help(string base, int pos, int size) {
	vector<string> ret;
	if (size <= 1) {
		ret.push_back(base);
		return ret;
	}
	while (base.at(pos) != 'X') pos++;
	vector<string> zero = help(base.substr(0, pos) + "0" + base.substr(pos + 1), pos + 1, size / 2);
	vector<string> one = help(base.substr(0, pos) + "1" + base.substr(pos + 1), pos + 1, size / 2);
	for (int i = 0; i < size; i++)
		if (i < size / 2) ret.push_back(zero.at(i));
		else ret.push_back(one.at(i - size / 2));
	return ret;
}