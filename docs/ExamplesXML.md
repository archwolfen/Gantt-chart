# Gantt-chart

## XML file

A file contains a list of tasks.
#### The structure 
- All activities are placed inside a `<chart-data>` tag.
- An `<activity>` tag describes concrete task.
    - A `title` attribute is used for setting a name of the task.
    - An `id` attribute specifies the identifier of the task. It must to be unique.
- A `<date>` tag describes the start and the end of the task. Date is presented
in timestamp. For example, `1551304800000` is equal `2019-02-27T22:00:00+00:00`
    - A `<start>` tag is placed inside `<date>` specifies the start date of the
    task. 
    - An `<end>` tag is placed inside `<date>` specifies the end date of the
    task.
- An `<executors>` tag describes executors of the task.
- To set an executor for the task an `<executor>` tag must be placed inside `<executors>`
    - A `<name>` tag specifies a first name of the executor.
    - A `<surname>` tag specifies a last name of the executor.
    - A `<photo>` tag specifies the photo of the executor. The URL is specified 
    with a `src` attribute.
- A `<progress>` tag describes a completion of the task in percentage.
- A `<dependencies>` tag describes tasks that must be completed before you can start
doing this task. If the task has no dependencies, this tag must be omitted.
    - To set dependencies for the task you must place an `<id>` tag which contains
    the id of previous task.
- A `<sub-activities>` tag contains `<activity>` tags which describe sub-tasks
of this task. If the task has no sub-tasks, `<sub-activities>` tag must be omitted.

#### An example of structure
```
<?xml version="1.0" encoding="utf-8"?>
<chart-data>
    <activity title="Name of task" id="Id of task">
        <date>
            <start>Start date of task in timestamp</start>
            <end>Finish date of task in timestamp</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Firstname of executor</name>
                <surname>Lastname of executor</surname>
                <photo src="URL to photo of executor"/>
            </executor>
        </executors>
        <progress>Progress of completion a task(in percentage)</progress><!--In %-->
        <dependencies>
            <id>Id of task which must be completed before we can start this task</id>
        </dependencies>
        <sub-activities>
            <activity title="" id="">
                <date>
                    <start></start>
                    <end></end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name></name>
                        <surname></surname>
                        <photo src=""/>
                    </executor>
                </executors>
                <progress></progress><!--In %-->
                <dependencies>
                    <id></id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
</chart-data>
```

## XSD schema

This file is used for validation structure of XML file
```<?xml version="1.0" encoding="UTF-8" ?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="chart-data">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="activity" type="activityType" maxOccurs="unbounded"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:complexType name="activityType">
        <xs:all>
            <xs:element name="date">
                <xs:complexType>
                    <xs:all>
                        <xs:element name="start" type="xs:positiveInteger"/>
                        <xs:element name="end" type="xs:positiveInteger"/>
                    </xs:all>
                </xs:complexType>
            </xs:element>
            <xs:element name="executors">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="executor" maxOccurs="unbounded">
                            <xs:complexType>
                                <xs:all>
                                    <xs:element name="name" type="xs:string"/>
                                    <xs:element name="surname" type="xs:string"/>
                                    <xs:element name="photo">
                                        <xs:complexType>
                                            <xs:attribute name="src" type="xs:anyURI"/>
                                        </xs:complexType>
                                    </xs:element>
                                </xs:all>
                            </xs:complexType>
                        </xs:element>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
            <xs:element name="progress" type="xs:positiveInteger"/>
            <xs:element name="dependencies" minOccurs="0">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="id" type="xs:string" maxOccurs="unbounded"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
            <xs:element name="sub-activities" minOccurs="0">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="activity" type="activityType" maxOccurs="unbounded"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
        </xs:all>
        <xs:attribute name="title" type="xs:string"/>
        <xs:attribute name="id" type="xs:string"/>
    </xs:complexType>
</xs:schema>
```

## XML examples

#### Correct XML
```
<?xml version="1.0" encoding="utf-8"?>
<chart-data>
    <activity title="Task1" id="1">
        <date>
            <start>1546293600000</start>
            <end>1546898400000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <sub-activities>
            <activity title="Sub-activity1.1" id="1.1">
                <date>
                    <start>1546293600000</start>
                    <end>1546380000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity1.2" id="1.2">
                <date>
                    <start>1546293600000</start>
                    <end>1546725600000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity1.3" id="1.3">
                <date>
                    <start>1546812000000</start>
                    <end>1546898400000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>1.1</id>
                    <id>1.2</id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
    <activity title="Task2" id="2">
        <date>
            <start>1547071200000</start>
            <end>1549576800000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>1</id>
        </dependencies>
    </activity>
    <activity title="Task3" id="3">
        <date>
            <start>1547071200000</start>
            <end>1550613600000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>1</id>
        </dependencies>
    </activity>
    <activity title="Task4" id="4">
        <date>
            <start>1549749600000</start>
            <end>1550613600000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>2</id>
        </dependencies>
    </activity>
    <activity title="Task5" id="5">
        <date>
            <start>1551045600000</start>
            <end>1552600800000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>4</id>
            <id>3</id>
        </dependencies>
        <sub-activities>
            <activity title="Sub-activity5.1" id="5.1">
                <date>
                    <start>1551045600000</start>
                    <end>1551218400000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity5.2" id="5.2">
                <date>
                    <start>1551045600000</start>
                    <end>1551564000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity5.3" id="5.3">
                <date>
                    <start>1551304800000</start>
                    <end>1551564000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>5.1</id>
                </dependencies>
            </activity>
            <activity title="Sub-activity5.4" id="5.4">
                <date>
                    <start>1551736800000</start>
                    <end>1552600800000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>5.2</id>
                    <id>5.3</id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
</chart-data>
```

#### XML with task dependencies error
```
<?xml version="1.0" encoding="utf-8"?>
<chart-data>
    <activity title="Task1" id="1">
        <date>
            <start>1546293600000</start>
            <end>1546898400000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <sub-activities>
            <activity title="Sub-activity1.1" id="1.1">
                <date>
                    <start>1546293600000</start>
                    <end>1546380000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity1.2" id="1.2">
                <date>
                    <start>1546293600000</start>
                    <end>1546725600000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity1.3" id="1.3">
                <date>
                    <start>1546812000000</start>
                    <end>1546898400000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>1.8</id><--Error-->
                    <id>1.2</id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
    <activity title="Task2" id="2">
        <date>
            <start>1547071200000</start>
            <end>1549576800000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>1</id>
        </dependencies>
    </activity>
    <activity title="Task3" id="3">
        <date>
            <start>1547071200000</start>
            <end>1550613600000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>1</id>
        </dependencies>
    </activity>
    <activity title="Task4" id="4">
        <date>
            <start>1549749600000</start>
            <end>1550613600000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>2</id>
        </dependencies>
    </activity>
    <activity title="Task5" id="5">
        <date>
            <start>1551045600000</start>
            <end>1552600800000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>4</id>
            <id>3</id>
        </dependencies>
        <sub-activities>
            <activity title="Sub-activity5.1" id="5.1">
                <date>
                    <start>1551045600000</start>
                    <end>1551218400000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity5.2" id="5.2">
                <date>
                    <start>1551045600000</start>
                    <end>1551564000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity5.3" id="5.3">
                <date>
                    <start>1551304800000</start>
                    <end>1551564000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>5.1</id>
                </dependencies>
            </activity>
            <activity title="Sub-activity5.4" id="5.4">
                <date>
                    <start>1551736800000</start>
                    <end>1552600800000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>5.2</id>
                    <id>5.3</id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
</chart-data>
```

#### XML with date bound error
```
<?xml version="1.0" encoding="utf-8"?>
<chart-data>
    <activity title="Task1" id="1">
        <date>
            <start>1546293600000</start>
            <end>1546898400000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <sub-activities>
            <activity title="Sub-activity1.1" id="1.1">
                <date>
                    <start>1546200000000</start><--Error-->
                    <end>1546380000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity1.2" id="1.2">
                <date>
                    <start>1546293600000</start>
                    <end>1546725600000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity1.3" id="1.3">
                <date>
                    <start>1546812000000</start>
                    <end>1546898400000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>1.1</id>
                    <id>1.2</id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
    <activity title="Task2" id="2">
        <date>
            <start>1547071200000</start>
            <end>1549576800000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>1</id>
        </dependencies>
    </activity>
    <activity title="Task3" id="3">
        <date>
            <start>1547071200000</start>
            <end>1550613600000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>1</id>
        </dependencies>
    </activity>
    <activity title="Task4" id="4">
        <date>
            <start>1549749600000</start>
            <end>1550613600000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>2</id>
        </dependencies>
    </activity>
    <activity title="Task5" id="5">
        <date>
            <start>1551045600000</start>
            <end>1552600800000</end><!--including-->
        </date>
        <executors>
            <executor>
                <name>Vladyslav</name>
                <surname>Manzhula</surname>
                <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
            </executor>
        </executors>
        <progress>67</progress><!--In %-->
        <dependencies>
            <id>4</id>
            <id>3</id>
        </dependencies>
        <sub-activities>
            <activity title="Sub-activity5.1" id="5.1">
                <date>
                    <start>1551045600000</start>
                    <end>1551218400000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity5.2" id="5.2">
                <date>
                    <start>1551045600000</start>
                    <end>1551564000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
            </activity>
            <activity title="Sub-activity5.3" id="5.3">
                <date>
                    <start>1551304800000</start>
                    <end>1551564000000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>5.1</id>
                </dependencies>
            </activity>
            <activity title="Sub-activity5.4" id="5.4">
                <date>
                    <start>1551736800000</start>
                    <end>1552600800000</end><!--including-->
                </date>
                <executors>
                    <executor>
                        <name>Kostiantyn</name>
                        <surname>Synytsia</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Anton</name>
                        <surname>Vovk</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                    <executor>
                        <name>Sviatoslav</name>
                        <surname>Konstantyniv</surname>
                        <photo src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/800px-User_icon_2.svg.png"/>
                    </executor>
                </executors>
                <progress>67</progress><!--In %-->
                <dependencies>
                    <id>5.2</id>
                    <id>5.3</id>
                </dependencies>
            </activity>
        </sub-activities>
    </activity>
</chart-data>
```