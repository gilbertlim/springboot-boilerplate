#!/bin/bash

# submodule 적용
# git submodule add [REPO_URL] [DIRECTORY]
rm -rf .git/modules
rm -rf src/main/java/com/megazone/springbootboilerplate/common_module
git submodule add git@github.com:gilbertlim/springboot-boilerplate-common.git src/main/java/com/megazone/springbootboilerplate/common_module

# submodule 최신화
git submodule update --remote