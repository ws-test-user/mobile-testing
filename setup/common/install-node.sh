#!/bin/bash

echo "Uninstall all previous node installations.."
sudo rm -rf /usr/local/{lib/node{,/.npm,_modules},bin,share/man}/{npm*,node*,man1/node*} && true; 
sudo rm -rf '/usr/local/lib/dtrace/node.d' && true; 
sudo rm -rf '/usr/local/etc/bash_completion.d/npm' && true; 
sudo rm -rf '/usr/local/include/node' && true; 
sudo rm -rf '/usr/local/lib/node_modules' && true; 
rm -rf '$HOME/.npm' && true; 
brew unlink node4-lts && true; 
brew uninstall --force node && true;
brew uninstall --force node6-lts && true; 
brew uninstall --force node@6 && true; 
brew uninstall --force node@8 && true;

echo "Install latest LTS release of NodeJS..."
brew install --force node@8

echo "Add node to path..."
if [[ ! -z $(grep "node@8" ~/.bash_profile) ]]; then 
    echo "Node 8 found in ~/.bash_profile"; 
else
    echo 'export PATH="/usr/local/opt/node@8/bin:$PATH"' >> ~/.bash_profile
fi
source ~/.bash_profile
