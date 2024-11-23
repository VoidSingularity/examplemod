# Berry Example Mod
This is the example mod of Berry Mod Loader.

## How to use it
Just clone this repository and run ```build.py main``` to build or
```build.py run_minecraft``` to test it!

_You need to have Python installed and run build.py with Python!_

If you want to start a new Berry mod, you can also clone this repository.
(Copyright notice: you have to remove existing code under src/ if you want to
license your mod under a different license. However, as the mods DO NOT use buildscripts
at runtime, you can use the existing buildscripts licensed under LGPL while using a
different license on your mod.)

## License
This mod is licensed undered the GNU Lesser General Public License v3.0 (or later),
see LICENSE.
Buildscripts (build.py, project.json, project.py, properties.json) are licensed under the
GNU Lesser General Public License v3.0 (or later), see LICENSE.
The buildscripts uses mapping.py, which contains code from
[DecompilerMC](https://github.com/hube12/DecompilerMC), licensed under the MIT License.

This mod uses [SpongePowered Mixin](https://github.com/SpongePowered/Mixin) at runtime,
which is licensed under the MIT License.

This mod uses SpecialSource in development, which has its own license
(see libs/LICENSE-SpecialSource.md).
