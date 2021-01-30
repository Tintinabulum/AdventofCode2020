#include <fstream>
#include <iostream>
#include <vector>
#include <utility>
#include <string>
using namespace std;
int main2(){
	ifstream input("Input14.txt");
	unsigned long long* mask = new unsigned long long[2];
	vector<pair<unsigned long long, unsigned long long>> mem = vector<pair<unsigned long long, unsigned long long>>();
	if (input.is_open()) {
		string line;
		while (getline(input, line)) {
			if (line.find("mask") == 0) {
				mask[0] = 0;
				mask[1] = -1;
				for (int i = line.find("=") + 2; i < line.size(); i++) {
					mask[0] = mask[0] << 1;
					mask[1] = mask[1] << 1;
					switch (line.at(i)) {
					case '1':
						mask[0] = mask[0] | 1;
					case 'X':
						mask[1] = mask[1] | 1;
					}
				}
			}else {
				unsigned long long index = stol(line.substr(line.find("[") + 1, line.find("]")));
				unsigned long long value = stol(line.substr(line.find("=") + 2));
				value = value | mask[0];
				value = value & mask[1];
				bool found = false;
				for (int i = 0; i < mem.size(); i++)
					if (mem.at(i).first == index) {
						mem.at(i).second = value;
						found = true;
						break;
					}
				if (!found)
					mem.push_back(pair<unsigned long long, unsigned long long>(index, value));
			}
		}
		unsigned long long int total = 0;
		for (pair<unsigned long long, unsigned long long> in : mem) {
			if (in.second < 0) cout << in.second << endl;
			total += in.second;
			//cout << in.first << " " << in.second << endl;
		}
		cout << total;
	}else {
		cout << "File not found.";
	}
	return 0;
}