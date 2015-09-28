#!/bin/sh
rpm_target_dir=/usr/src/packages
rpm_spec_name=cs.spec
rpmsource=/usr/src/packages/SOURCES

cur_path=`pwd`
rm -rf *.tar
ant clean
ant
ant tarfile
mv *.tar ${rpmsource}
dos2unix ${rpm_spec_name}
rpmbuild -bb ${rpm_spec_name} --define="_topdir ${rpm_target_dir}"
cd ${cur_path}
