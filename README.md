model-based-testing-automation-practice [![Build Status](https://travis-ci.org/GraphWalker/graphwalker-example.svg?branch=master)](https://travis-ci.org/GraphWalker/graphwalker-example) [![Codeship Status for roachhd/Notepad](https://codeship.com/projects/e5072e80-833c-0133-fb5f-2e117485f168/status)](https://codeship.com/projects/121755)
===================

# Model Based Testing Automation Practice

This is an example of model based testing based on [Pet Clinic Example](https://github.com/GraphWalker/graphwalker-example/tree/master/java-petclinic). The software under test is [Automation Practice](http://automationpractice.com)

### Prerequisites

You need to install these software to run this example.

* Java JDK version 8 installed
* Maven installed (version equal or greater than 3.2.3)
* git installed
* Latest Chrome installed

## Getting Started

First you need to download the code. And then generate source code from models:

```
git clone git@github.com:tienvx/model-based-testing-automation-practice.git
cd model-based-testing-automation-practice
mvn graphwalker:generate-sources
```

Then you can open code using your favorite ide.

The models are locate at src/main/java/resources/com/company. We can open
and edit them using [yEd](http://www.yworks.com/en/products/yfiles/yed/).
Generated code locate at target/generated-sources/graphwalker/com/company

## Running the tests

Simply run this command

```
mvn graphwalker:test
```

## Built With

* [GraphWalker](http://graphwalker.github.io) - The model based testing tool
* [Maven](https://maven.apache.org/) - Dependency Management
* [yEd](http://www.yworks.com/en/products/yfiles/yed/) - Used to view and edit our models

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Tien Vo** - *Initial work* - [tienvx](https://github.com/tienvx)

See also the list of [contributors](https://github.com/tienvx/model-based-testing-automation-practice/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Pet Clinic](http://graphwalker.github.io/petclinic/) example from [GraphWalker](http://graphwalker.github.io)
* [Great tutorial](http://testoptimal.com/ref/starwest-2006-mbt-tutorial.pdf) from [Harry Robinson](harryrobinson.net)
